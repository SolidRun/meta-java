inherit native

INC_FILE_SUFFIX = ""
INC_FILE_SUFFIX_aarch64 = "-aarch64"
INC_FILE_SUFFIX_armv7a = "-aarch32"
INC_FILE_SUFFIX_armv7ve = "-aarch32"
require openjdk-8-release${INC_FILE_SUFFIX}.inc
require openjdk-8-native.inc
