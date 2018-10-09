package org.wpattern.ktor.config

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding
import io.ktor.application.Application
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.wpattern.ktor.product.ProductService
import org.wpattern.ktor.product.ProductServiceImpl
import org.wpattern.ktor.commons.accumulate
import org.wpattern.ktor.event.EventRepository
import org.wpattern.ktor.product.ProductEvent
import org.wpattern.ktor.product.ProductFileEventRepository
import java.io.File

fun Application.configKoin() {
    val module = module {
        single { ProductFileEventRepository(eventName = "product", folderPath = getProperty("product.folder")) }
        single { ProductServiceImpl(get()) as ProductService }
    }

    val profile = environment.config.property("ktor.profile").getString()
    StandAloneContext.startKoin(
            list = listOf(module),
            useKoinPropertiesFile = true,
            useEnvironmentProperties = true,
            extraProperties = loadProfile(profile))
}

private fun loadProfile(profile: String): Map<String, String> {
    val profileFilename = "application-$profile.properties"

    val configuration = ConfigurationProperties.systemProperties() overriding
            EnvironmentVariables() overriding
            ConfigurationProperties.fromFile(File("src/main/resources$profileFilename")) overriding
            ConfigurationProperties.fromResource(profileFilename)

//    val configuration = ConfigurationProperties.systemProperties().overriding(
//            EnvironmentVariables().overriding(
//                    ConfigurationProperties.fromFile(File("src/main/resources$profileFilename")).overriding(
//                            ConfigurationProperties.fromResource(profileFilename)
//                    )
//            )
//    )

    return configuration
            .list()
            .map { it.second }
            .accumulate()
}
