package com.example.systemdesign.epam.misllenious

import kotlin.jvm.java


class KotlinCodingPractice {

    /**🔥High order function*/

    /** Question1 :  write high order function  who support for add , subtract and multiply of 2 float */

    fun operationSupport(a: Float,b: Float, operation:(Float, Float)-> Float): Float{
       return operation(a,b)
    }
    fun add(a: Float, b: Float): Float{
      return operationSupport(a,b,{a,b->
         a+b
        })
    }
    fun subtract(a: Float, b: Float): Float{
        return operationSupport(a,b,{a,b->
            a-b
        })
    }
    fun multiply(a: Float, b: Float): Float{
        return operationSupport(a,b,{a,b->
            a*b
        })
    }

    /** Question1 :  write a function  who return a function and use for add ,subtract, multiply   */

    fun calculate(type: String):(Int, Int)-> Int{
      return  when(type){
            "add" ->{
               {a,b ->a+b}
            }
            "subtract" ->{
                  {a,b ->a-b}
            }
            "multiply" ->{
                  {a,b ->a*b}
            }
          else -> {a,b,->0}
      }
    }

    fun add(a: Int,b: Int): Int{
        val adding = calculate("add")
       return adding(a,b)
     }

    fun subtract(a: Int,b: Int): Int{
        val subtract = calculate("subtract")
        return subtract(a,b,)
    }

    fun multiply(a: Int,b: Int): Int{
        val multiply = calculate("multiply")
        return multiply(a,b)
    }

    /**🔥Inline function*/

   inline fun add(a: Int,b: Int, calculate:(Int, Int)-> Int){
        calculate(a,b)
    }
    fun callInlineAdd(a: Int,b: Int){
        add(a,b){a,b->
            a+b

        }
    }

    inline fun add(a: Int,b: Int, calculate:(Int, Int)-> Int , noinline callback:()-> Unit){
        calculate(a,b)
    }


    inline fun addWithCrossInline(a:Int,b: Int,crossinline calculate: (Int, Int)-> Unit){

        calculate(a,b)
    }

    fun callCrossLineAdd(a: Int,b: Int){
        addWithCrossInline(a,b){a,b->
          a+b
        /*  return  ->  local return not allow because it is crossInLine  */
          return@addWithCrossInline   /** local return is allowed*/
         }

    }


    /**🔥reified function*/

    inline fun <reified T> get(){
       print(T::class.java)
     }










}