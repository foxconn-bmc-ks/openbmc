require recipes-bsp/u-boot/u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
DEPENDS += "dtc-native"

SRCREV = "4d86498d794fff30d8e7c6192eb4cb46af911c74"
UBRANCH = "foxconn-v2016.07-aspeed-openbmc"
SRC_URI = "git://git@github.com/foxconn-bmc-ks/u-boot.git;branch=${UBRANCH};protocol=https"

PV = "v2016.07+git${SRCPV}"
