import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.serializer.SerializerFeature
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.FileSystemNotFoundException


fun getJsonFromSp(fileName: String, key: String): String {
    val file = File(fileName)
    if (file.exists().not()) {
        throw FileSystemNotFoundException(fileName)
    }
    var json = FileUtils.readFileToString(file, "UTF-8")
    json = json.replace("&quot;", "\"")
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