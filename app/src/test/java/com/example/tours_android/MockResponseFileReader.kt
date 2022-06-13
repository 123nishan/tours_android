package com.example.tours_android

import java.io.InputStreamReader
/**
 * @param path: path to the file
 * explantion:
 *
 The class MockResponseFileReader is defined with a constructor that takes a path as a parameter.
 The init block is executed when the object is created.
 The content variable is initialized with the content of the file.
 The close() method is called on the reader object.
 The content of the file is returned.
  */
class MockResponseFileReader(path:String) {
    val content:String
    init {
       val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        content= reader.readText()
        reader.close()
    }
}
