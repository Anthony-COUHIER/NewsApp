package anthony.project.news.data

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

object DataConstant {
    object Remote {
        const val baseUrl: String = "https://newsapi.org/"


        const val defaultCountry: String = "us"
        val countryAvailableList: List<String> = listOf(
            "ae", "ar", "at", "au", "be", "bg", "br", "ca",
            "ch", "cn", "co", "cu", "cz", "de", "eg", "fr",
            "gb", "gr", "hk", "hu", "id", "ie", "il", "in",
            "it", "jp", "kr", "lt", "lv", "ma", "mx", "my",
            "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro",
            "rs", "ru", "sa", "se", "sg", "sisk", "th", "tr",
            "tw", "ua", "us", "ve", "za",
        )

        val sources = listOf(
            "the-verge", "vice-news", "wired", "bbc-news", "abc-news", "cnn",
            "reuters", "al-jazeera-english", "the-new-york-times", "the-guardian", "vox",
            "polygon", "ign", "cnet", "forbes", "business-insider", "esquire"
        )

        const val defaultLanguage: String = "en"
        val languageAvailableList: List<String> = listOf(
            "ar", "de", "en", "es", "fr", "he", "it",
            "nl", "no", "pt", "ru", "sv", "ud", "zh"
        )

        const val defaultHeadlinesPageSize: Int = 20
        const val defaultAllNewsPageSize: Int = 99

        @OptIn(FormatStringsInDatetimeFormats::class)
        val dateTimeFormat: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
            byUnicodePattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        }
    }
}