package io.rybalkinsd.kotlinbootcamp.practice
import java.util.*

class RawProfile(val rawData: String)
/**
 * Here are Raw profiles to analyse
 */
val rawProfiles = listOf(
        RawProfile("""
            fisrtName=alice,
            age=16,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            fisrtName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            fisrtName=alla,
            lastName=OloOlooLoasla,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            fisrtName=bella,
            age=36,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            fisrtName=angel,
            age=I will not tell you =),
            source=facebook
            """.trimIndent()
        ),

        RawProfile(
                """
            lastName=carol,
            source=vk
            age=49,
            """.trimIndent()
        ),
        RawProfile("""
            fisrtName=bob,
            lastName=John,
            age=47,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            lastName=kent,
            fisrtName=dent,
            age=88,
            source=facebook
            """.trimIndent()
        )
)



enum class DataSource{
    FACEBOOK,
    VK,
    LINKEDIN
}

sealed class Profile(
    var id: Long,
    var firstName: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var dataSource: DataSource
)

/**
 * Task #1
 * Declare classes for all data sources
 */
class FacebookProfile(id: Long) : Profile(dataSource = DataSource.FACEBOOK, id = id)
class VkProfile(id: Long) : Profile(dataSource = DataSource.VK, id = id)
class LinkedinProfile(id: Long) : Profile(dataSource = DataSource.LINKEDIN, id = id)
val profiles: List<Profile> = makeProfiles(rawProfiles)
/**
 * Task #2
 * Find the average age for each datasource (for profiles that has age)
 */
val avgAge: Map<DataSource, Double> = profiles
        .groupBy { it.dataSource }
        .mapValues { entry -> entry.value.asSequence().filter { it.age != null }.map { it.age!!.toDouble() }.average() }
/**
 * Task #3
 * Group all user ids together with all profiles of this user.
 * We can assume users equality by : firstName & lastName & age
 */
val groupedProfiles: Map<Long, List<Profile>> = profiles.groupBy { listOf(it.age, it.lastName, it.firstName) }
        .values.associateBy { (0L..100L).random() }



fun LongRange.random() = Random().nextInt((endInclusive.toInt() + 1) - start.toInt()) + start

fun makeProfiles(rawProfiles: List<RawProfile>): List<Profile> {
    var tmp : String
    var res : List<Profile>
    res = emptyList()
    for (prof in rawProfiles)
    {
        tmp = prof.rawData.toLowerCase()
        var tmp2 = when (tmp.substringAfter("source=").substringBefore(",")) {
            "linkedin" -> LinkedinProfile((0L..100L).random())
            "vk" -> VkProfile((0L..100L).random())
            "facebook" -> FacebookProfile((0L..100L).random())
            else -> {
                var tmp3 = tmp.substringAfter("source=").substringBefore(",")
                println("string = $tmp3")
                throw Exception("Error in profile source")}
        }
        if (tmp.contains("firstname")) tmp2.firstName = tmp.substringAfter("firstname=").substringBefore(",")
        if (tmp.contains("lastname")) tmp2.lastName = tmp.substringAfter("lastname=").substringBefore(",")
        try {
            if (tmp.contains("age")) tmp2.age = tmp.substringAfter("age=").substringBefore(",").toInt()
        } catch (e: Exception) {}
        res+=tmp2
    }
    return res
}
