SUMMARY = "A compact Java Virtual Machine which conforms to the JVM specification version 2."
HOMEPAGE = "http://jamvm.sourceforge.net/"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

DEPENDS = "zlib-native classpath-initial-native jikes-initial-native libffi-native"

PROVIDES = "virtual/java-initial-native"

PR = "r1"

S = "${WORKDIR}/jamvm-${PV}"

SRC_URI = "${SOURCEFORGE_MIRROR}/jamvm/jamvm-${PV}.tar.gz \
           file://jamvm-initial.patch \
           file://jamvm-1.5.1-aarch64-support.patch \
           file://java-initial \
          "

# This uses 32 bit arm, so force the instruction set to arm, not thumb
ARM_INSTRUCTION_SET = "arm"

inherit autotools pkgconfig native

# libdir must be modified so that jamvm-initial and -native
# do not interfere
EXTRA_OECONF = "\
  --with-classpath-install-dir=${prefix} \
  --program-suffix=-initial \
  --libdir=${STAGING_LIBDIR}/jamvm-initial \
  --enable-ffi \
  --disable-int-threading \
  "

# jamvm-initial has to run some binaries which need lots of memory.
CFLAGS += "-DDEFAULT_MAX_HEAP=512*MB"

# Enforce usage of jikes-initial.
EXTRA_OEMAKE = "JAVAC=${STAGING_BINDIR_NATIVE}/jikes-initial \
                GLIBJ_ZIP=${STAGING_DATADIR_NATIVE}/classpath-inital/glibj.zip \
               "
do_install_append() {
  install -d ${D}${bindir}/
  install -m 0755 ${WORKDIR}/java-initial ${D}${bindir}/
}

SRC_URI[md5sum] = "5a82751b50391eb092c906ce64f3b6bf"
SRC_URI[sha256sum] = "663895bd69caf3a1fda6af5eea8263d90a5fd35ca8f4c32e2210ac410788901a"
# shared state for jamvm-native does not work
# since the paths are hardcoded
#SSTATE_MIRRORS_class-native = ""
