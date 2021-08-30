# Note: You *must* use this together with classpath-native 0.98.
# Otherwise it won't work!

require jamvm.inc

SRCREV = "236f9d849cf52faeb150a6cd5ba6fbeb61bd2f1f"
PV = "2.0.0-devel+git${SRCPV}"

SRC_URI = "git://git.code.sf.net/p/jamvm/code;protocol=git \
           file://libffi.patch \
           file://jamvm-minmax-heap.patch \
           file://jamvm-2.0.0-aarch64-support.patch \
           file://jamvm-2.0.0-disable-branch-patching.patch \
           file://jamvm-2.0.0-opcode-guard.patch \
           file://java \
          "

S = "${WORKDIR}/git"
