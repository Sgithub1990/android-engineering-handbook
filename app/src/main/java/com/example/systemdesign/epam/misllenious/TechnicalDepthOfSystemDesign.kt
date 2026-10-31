package com.example.systemdesign.epam.misllenious

/**
🔥Concurrency Control
 It ensure concurrent operation on shared  resource execute safely without data inconsistency and race condition

🔸1- Atomic SQL update
🔹why use :
When multiple coroutines or WorkManager workers process the same record

🔹Syntax:
@Query("Update account SET balance= balance -:amount where id = :id" And balance>=:amount)
fun withdraw(amount, id):Int

🔹Concept
Condition	                                   Return value	                 Meaning
Account exists and balance is sufficient	   1	                         Withdrawal succeeded
Account doesn't exist	                       0	                         No row updated
Balance is insufficient                        0	                         No row updated

🔹what happen without atomic update:
Two coroutines withdraw money concurrently:
- Coroutine A withdraws ₹200.
- Coroutine B withdraws ₹300.
❌ Without atomic update

Coroutine A
Reads ₹1,000
Calculates ₹800

Coroutine B
Reads ₹1,000
Calculates ₹700

Final balance could be ₹800 or ₹700
✅Expected balance: ₹500


🔸2- Room transactions
🔹why use :
when two database operation is dependant then use transaction

🔹Syntax:
 @Transaction{
 }

🔹Example:
 1- Read balance → validate → update balance
 2- Insert order + insert order items
 3-Update multiple related tables

🔸3- mutex
🔹why use :
when multiple coroutine update shared data or variable

🔹Syntax:
val mutex = Mutex()
mutex.withLock{
}

🔹Example:
Prevent concurrent token refresh

🔸4- Atomic operation
🔹why use :
atomic operations to prevent race conditions when multiple threads or coroutines modify the same shared value at the same time.

🔹Syntax :
val count = AtomicInteger(0)
count.incrementAndGet()

 */


/**
🔥 Prevent Duplicate records
🔸Use stable key and  Upsert
 */

/**
🔥 Prevent Duplicate Operation like APi Call, withdraw
🔸Use Idempotency Key

🔹Why Use
1-Android sends a transfer request to the server.
2-The server processes the transfer and deducts ₹1,000.
3-The response gets lost because of a network timeout.
4-Android retries the request.

🔹Without idempotency:
The server might deduct ₹1,000 again.

🔹With idempotency
The server then:
1-Checks whether the key has already been processed.
2-If it's new, processes the transfer and stores the result associated with that key.
3-If it's a retry with the same key, returns the previously stored result instead of processing the transfer again.

🔸How does Android reuse the key?
 1- User initiates payment & Generate key: payment-123
 2-Save payment in Room & Store payment details, status and idempotency key.
 3- Read the existing payment and key from Room. when retry
 */


/**
🔥How to handle offline write
🔸schedule with workManager
1- when Offline update Room DB
2- Set Status to Pending
3- Schedule workManager for sync

* */