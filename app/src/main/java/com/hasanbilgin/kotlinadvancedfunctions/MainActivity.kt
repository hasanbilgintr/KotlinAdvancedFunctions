package com.hasanbilgin.kotlinadvancedfunctions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //null atamak istersek ? zorunlu kılar
    private var myInt: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val myNumList = listOf<Int>(1, 3, 5, 7, 9, 11, 13, 15)
        //6dan küçükleri almak istersek filter ile
        //filter
//        val smallNumberList = myNumList.filter { num -> num < 6 }//yada
//        val smallNumberList = myNumList.filter { it -> it < 6 }//yada
        val smallNumberList = myNumList.filter { it < 6 }
        for (item in smallNumberList) {
            println(item)
        }// 1 3 5

        //map başka bir hale getirmek
        val squaredNumbers = myNumList.map { it * it }
        for (item in squaredNumbers) {
            println(item)
        }//1 9 25 49 ........... 121

        //sadece 6dan küçükler ve kendi ile çarpımlarını vericek ilk önce 6dan küçükler sonra kendi ile çaprpıcak
        val filterAndMapCombined = myNumList.filter { it < 6 }.map { it * it }
        for (sayi in filterAndMapCombined) {
            println(sayi)
        }//1 9 25


        val musician = listOf<Musician>(Musician("James", 60, "Guitar"), Musician("Lars", 55, "Drum"), Musician("Kirk", 50, "Guitar"))
        //60tan küçük olan ve sadece instürmanları gelcek
        val musician60small = musician.filter { it.age < 60 }.map { it.instrument }
        //map bu şekilde List<String> dönüşmüş oldu  map olmasaydı List<Musician> olarka verirdi
        for (item in musician60small) {
            println(item)
        }//drum  Guitar

        //predicate-----
        //bu ise tüm liste 5ten büyükmü true folse döndüdür
        val allCheck = myNumList.all { it > 5 }
        println(allCheck) //false

        //bu ise listedeki herhangi biri 5ten büyükmü
        val anyCheck = myNumList.any { it > 5 }
        println(anyCheck)//true

        //5ten büyük kaç eleman var
        val totalCount = myNumList.count { it > 5 }
        println(totalCount)//5

        //5ten büyük ilk elemanı bul ve ver
        val findNum = myNumList.find { it > 5 }
        println(findNum)//7 yoksa null verir

        //5ten büyük en son elemanı bul ve ver
        val findNumLast = myNumList.findLast { it > 5 }
        println(findNumLast)//15

        //önceden predicate tanımlayıp atamak
        //ve burda num integer dir diye tanımlıyorumz ve tüm sayılar 5 ten büyükse true verir
        val myPredicate = { num: Int -> num > 5 }
        val allCheckWithPredicate = myNumList.all(myPredicate)
        println(allCheckWithPredicate)// false


        //let ve also
        if (myInt != null) {
            //!! koydudur
            val num = myInt!! + 1
        }

        //myInt.let { böle ise it!! ister
        myInt?.let {
            val num = it + 1
        }

        val myNum = myInt?.let {
            it + 1
        } ?: 0 //?:0 ifadesi nullsa 0 ata  demek

        //also şunuda anlamaına geliyo
        val atil = Person("Atil", 35)
        val atlas = Person("Atlas", 1)
        val zeynep = Person("Zeynep", 27)

        val people = listOf<Person>(atil, atil, zeynep)

        //yaşı 18 den küçükleri atıcak parantez içinde also ile forla dönbilcez ..
        people.filter { it.age > 18 }.also {
            for (person in it) {
                println(person.name)
            }
        }

        //apply
        //objeleri modifiye edicez çokta uğraşmıcaz kendi parantez içinde halldiyo
        //retrofit.builderde örnektir.....
        val intent = Intent()
        intent.putExtra("", "")
        intent.putExtra("", "")
        intent.`package` = ""
        intent.action = "";
        //startActivity(intent)//........
        //yerine
        val intentWithApply = Intent().apply {
            this.putExtra("", "")//aynı
            putExtra("", "")
            `package` = ""
            action = ""
        }

        //with //ile birlikte
        Person("barley",4).apply {
            name="Barley"
        }.also {
            println(it.name)
        }
        //Barley yazdırdı//alttakide aynı yani bölede yaptırılabilir

        val newBarley=Person("bar",4).apply {
            name="Barley"
        }
        println(newBarley.name)
        //Barley

        val anotherBarley= with(Person("arley",4)){
            name="Barley"
        }
        println(anotherBarley)// kotlin.Unit// yani boş döndürüyo

        val withBarley=Person("arley",4)
        with(withBarley){
            name="Barley"
            age=4
        }
        println(withBarley.name)//Barley


    }

}

data class Person(var name: String, var age: Int)

data class Musician(val name: String, val age: Int, val instrument: String)