package io.rybalkinsd.kotlinbootcamp.practice
import java.util.Random
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
enum class DataSource {
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
val avgAge: Map<DataSource, Double> = make_avgAge(profiles)
fun make_avgAge(profs: List<Profile>): Map<DataSource, Double> {
    var res: Map<DataSource, Double>
    var tmp_sum = mutableMapOf<DataSource, Double>()
    var tmp_number = mutableMapOf<DataSource, Int>()
    tmp_sum[DataSource.FACEBOOK] = 0.0
    tmp_sum[DataSource.VK] = 0.0
    tmp_sum[DataSource.LINKEDIN] = 0.0
    tmp_number[DataSource.FACEBOOK] = 0
    tmp_number[DataSource.VK] = 0
    tmp_number[DataSource.LINKEDIN] = 0

    for (prof in profs) {
            if (prof.age != null) {
                var k1 = tmp_sum[prof.dataSource]
                var r1 = k1!! + prof.age!!

                var k2 = tmp_number[prof.dataSource]
                var r2 = k2!! + 1
                if (k1 != null) {
                    tmp_sum[prof.dataSource] = r1
                }
                if (k2 != null) {
                    tmp_number[prof.dataSource] = r2
                }
                }
            }
    var t = tmp_sum[DataSource.FACEBOOK]
    var t2 = tmp_number[DataSource.FACEBOOK]
    if (t != null) {

        tmp_sum[DataSource.FACEBOOK] = t / t2!!
    }
    var ta = tmp_sum[DataSource.VK]
    var ta2 = tmp_number[DataSource.VK]
    if (ta != null) {

        tmp_sum[DataSource.VK] = ta / ta2!!
    }
    var tb = tmp_sum[DataSource.LINKEDIN]
    var tb2 = tmp_number[DataSource.LINKEDIN]
    if (tb != null) {

        tmp_sum[DataSource.LINKEDIN] = tb / tb2!!
    }
    res = tmp_sum
    return res
    }
/**
 * Task #3
 * Group all user ids together with all profiles of this user.
 * We can assume users equality by : firstName & lastName & age
 */
val groupedProfiles: Map<Long, List<Profile>> = profiles.groupBy { listOf(it.age, it.lastName, it.firstName) }
        .values.associateBy { (0L..100L).random() }

fun LongRange.random() = Random().nextInt((endInclusive.toInt() + 1) - start.toInt()) + start

fun makeProfiles(rawProfiles: List<RawProfile>): List<Profile> {
    var tmp: String
    var res: List<Profile>
    res = emptyList()
    for (prof in rawProfiles) {
        tmp = prof.rawData.toLowerCase()
        var tmp4 = tmp.substringAfter("source=").substringBefore("\n")
        println("tmp4 = $tmp4")
        var tmp2 = when (tmp4.substringBefore(",")) {
            "linkedin" -> LinkedinProfile((0L..100L).random())
            "vk" -> VkProfile((0L..100L).random())
            "facebook" -> FacebookProfile((0L..100L).random())
            else -> {
                var tmp3 = tmp.substringAfter("source=").substringBefore(",")
                println("string = $tmp3")
                throw Exception("Profile source is invalid")
            }
        }
        if (tmp.contains("firstname")) tmp2.firstName = tmp.substringAfter("firstname=").substringBefore(",")
        if (tmp.contains("lastname")) tmp2.lastName = tmp.substringAfter("lastname=").substringBefore(",")
        try {
            if (tmp.contains("age")) tmp2.age = tmp.substringAfter("age=").substringBefore(",").toInt()
        } catch (e: Exception) {}
        res += tmp2
    }
    return res
}
