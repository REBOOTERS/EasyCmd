import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.serializer.SerializerFeature
import engineer.easy.dev.xml2json.bean.MaterialDownload
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.FileSystemNotFoundException


fun readBootAdSp() {
    val fileName = "C:\\Users\\zhuyongqing01\\Desktop\\ad-data-proj\\shared_prefs\\boot_ad_sp.xml"
    val key = "boot_ad_payload_key"
    var json = getJsonFromSp(fileName, key)

    val material = JSON.parseObject(json, MaterialDownload::class.java)
    println(material.version)
//    material.version = 100
    json = JSON.toJSONString(material)
    val beauty = formatJson(json)

    println(beauty)
//    log(beauty)
}

fun getJsonFromSp(fileName: String, key: String): String {
    val file = File(fileName)
    if (file.exists().not()) {
        throw FileSystemNotFoundException(fileName)
    }
    var json = FileUtils.readFileToString(file, "UTF-8")
    json = json.replace("&quot;", "\"")
    json = sharedPreferenceToJson(key, json)
    return json
}


fun sharedPreferenceToJson(key: String, originalStr: String): String {
    var json = originalStr
    json = json.replace("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>", "")
    json = json.replace("<map>", "")
    json = json.replace("</map>", "")
    json = json.replace("<string name=\"$key\">", "")
    json = json.replace("</string>", "")
    return json
}

fun formatJson(json: String): String {
    val jsonObj = JSONObject.parse(json)
//    val msg = Exception("100").stackTraceToString()
//    println(msg)
    return JSON.toJSONString(
        jsonObj, SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.MapSortField,
//        SerializerFeature.BrowserCompatible,
        SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat
    )
}