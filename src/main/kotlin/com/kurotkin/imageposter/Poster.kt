package com.kurotkin.imageposter

import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.core.io.FileSystemResource
import org.springframework.http.*
import org.springframework.http.HttpEntity
import java.io.*
import java.net.URL


class Poster {

    fun postImage(){


    }


    fun post(){
        val map = LinkedMultiValueMap<String, Any>()
        map.add("file", FileSystemResource("temp2.txt"))
        //map.add("file", ClassPathResource("temp2.txt"))
        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val requestEntity = HttpEntity(map, headers)
        val response = RestTemplate().exchange("http://", HttpMethod.POST, requestEntity, String::class.java)
    }

    fun get(){
        val url = "http://"

        val input = URL(url).openStream()
        val output = FileOutputStream("logo.gif") as OutputStream?

        val data = ByteArray(4096)
        var count = 0
        while (input.read(data).also { count = it } != -1){
            output?.write(data, 0, count)
        }

        input?.close()
        output?.close()
    }

}