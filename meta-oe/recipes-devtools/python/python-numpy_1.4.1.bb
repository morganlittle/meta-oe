DESCRIPTION = "A sophisticated Numeric Processing Package for Python"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3c42211d73c3faa1026e671dd39883fa"
PR = "r5"

SRC_URI = "${SOURCEFORGE_MIRROR}/numpy/numpy-${PV}.tar.gz \
           ${CONFIGFILESURI} \
	  "
CONFIGFILESURI ?= ""

CONFIGFILESURI_arm = "file://config.h \
	   file://numpyconfig.h \
	  "
CONFIGFILESURI_mipsel = "file://config.h \
	   file://numpyconfig.h \
	  "

S = "${WORKDIR}/numpy-${PV}"

inherit distutils

# Make the build fail and replace *config.h with proper one
# This is a ugly, ugly hack - Koen
do_compile_prepend() {
         BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} \
         ${STAGING_BINDIR_NATIVE}/python-native/python setup.py build ${DISTUTILS_BUILD_ARGS} || \
         true
	 cp ${WORKDIR}/*config.h ${S}/build/$(ls ${S}/build | grep src)/numpy/core/include/numpy/
}

SRC_URI[md5sum] = "5c7b5349dc3161763f7f366ceb96516b"
SRC_URI[sha256sum] = "2e7bb84573e5123e07f3c919fd433bc09b78d606252b6b719e385c2a981d8e06"

FILES_${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/numpy/core/lib/*.a"

RDEPENDS_${PN} = "python-unittest \
                  python-difflib \
                  python-pprint \
                  python-pickle \
                  python-shell \
                 "
