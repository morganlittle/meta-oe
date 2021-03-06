require ${BPN}.inc

SRCREV = "${EFL_SRCREV}"
PV = "1.7.1+svnr${SRCPV}"
PR = "${INC_PR}.0"
DEFAULT_PREFERENCE = "-1"

SRCNAME_append = "-1.7"

SRC_URI = "\
  ${E_SVN}/branches;module=${SRCNAME};protocol=http;scmdata=keep \
  file://fix-ecore-fb-initialization.patch \
"
S = "${WORKDIR}/${SRCNAME}"
