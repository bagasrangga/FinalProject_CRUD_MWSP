package com.mws.finalproject_crud_mwsp

class ApiEndPoint {
    companion object {

        private val SERVER = "http://192.168.100.24/crud_mwsp/"
        val CREATE = SERVER+"create.php"
        val READ = SERVER+"read.php"
        val DELETE = SERVER+"delete.php"
        val UPDATE = SERVER+"update.php"

    }
}