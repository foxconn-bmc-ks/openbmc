DESCRIPTION = "IPMI over LAN (RMCP/IPMIv1.5) Server"
LICENSE = "Apache-2.0"
PV = "v5.10-rc3"

HOMEPAGE = "https://github.com/foxconn-bmc-ks/fru-util"

DEPENDS += "zlog"
inherit obmc-phosphor-license
inherit obmc-phosphor-systemd
INSANE_SKIP_fru-util += "dev-deps"
INSANE_SKIP_fru-util-dev += "dev-elf"

BB_NO_NETWORK = "0"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
RDEPENDS_${PN} = "bash python"

EXTRA_OEMAKE += " CC='${CC}' LD='${LD}' LIB_VERSION=${PV}  V=1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

FOXCONN_BRANCH="foxconn-g2"
SRC_URI = "git://github.com/foxconn-bmc-ks/fru-util;branch=${FOXCONN_BRANCH}"
SRC_URI += "file://fru-inventory-gen.py"
SRCREV = "de10d83258a3ddcdab779993cd6fb3f9208e85cb"
S = "${WORKDIR}/git"
SYSTEMD_SERVICE_${PN} += "fru-inventory-gen.service"

do_compile() {
        oe_runmake all
}

do_install() {
        oe_runmake  PREFIX=${D} install
        install -Dm0755 ${WORKDIR}/fru-inventory-gen.py ${D}/${sbindir}/fru-inventory-gen.py
}
