package com.kurotkin.imageposter

import org.slf4j.LoggerFactory
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.core.io.FileSystemResource
import org.springframework.http.*
import org.springframework.http.HttpEntity
import java.io.*
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class Poster {

    companion object {
        const val FROM_URL = "http://"
        const val TO_URL = "http://"
    }

    private val log = LoggerFactory.getLogger("Poster")

    fun postImage(){
        val fileName = get()
        if(fileName == null) log.info("Bad file download")
        else {
            post(fileName)
        }
    }


    private fun post(fileName: String){
        val map = LinkedMultiValueMap<String, Any>()
        map.add("file", FileSystemResource(fileName))
        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val requestEntity = HttpEntity(map, headers)
        val response = RestTemplate().exchange(TO_URL, HttpMethod.POST, requestEntity, String::class.java)
        deleteFile(fileName)
    }

    private fun get() : String?{
        var input: InputStream? = null
        var output: OutputStream? = null
        val fileName: String
        try {
            fileName = genName()
            val input = URL(FROM_URL).openStream()
            val output = FileOutputStream(fileName) as OutputStream?

            val data = ByteArray(4096)
            var count: Int
            while (input.read(data).also { count = it } != -1){
                output?.write(data, 0, count)
            }
        } catch (e: Exception){
            e.printStackTrace()
            return null
        } finally {
            input?.close()
            output?.close()
        }
        return fileName
    }

    private fun deleteFile(fileName: String){
        FileSystemResource(fileName).file.delete()
    }

    private fun genName() : String {
        val format = "yyyy-MM-dd_HH-mm-ss"
        val date = Date()
        val dateStr = SimpleDateFormat(format).format(date)
        return "image_$dateStr.jpg"
    }

}