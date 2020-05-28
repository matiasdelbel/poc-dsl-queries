include(":app")

include(":person:domain")
project(path = ":person:domain").projectDir = File(rootDir, "person/domain")


include(":person:presentation")
project(path = ":person:presentation").projectDir = File(rootDir, "person/presentation")


include(":person:gateway")
project(path = ":person:gateway").projectDir = File(rootDir, "person/gateway")