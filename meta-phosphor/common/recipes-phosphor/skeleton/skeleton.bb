SUMMARY = "Temp placeholder for skeleton function"
DESCRIPTION = "Temp placeholder for skeleton repository"
HOMEPAGE = "http://github.com/openbmc/skeleton"
PR = "r1"

inherit obmc-phosphor-license
inherit obmc-phosphor-systemd
inherit obmc-phosphor-chassis-mgmt
inherit obmc-phosphor-fan-mgmt
inherit obmc-phosphor-flash-mgmt
inherit obmc-phosphor-policy-mgmt
inherit obmc-phosphor-sensor-mgmt
inherit obmc-phosphor-system-mgmt
inherit pythonnative
inherit python-dir

VIRTUAL-RUNTIME_skeleton_workbook ?= ""

DEPENDS += "glib-2.0 i2c-tools systemd python"
RDEPENDS_${PN} += "python-argparse python-json python-subprocess python-compression libsystemd ${VIRTUAL-RUNTIME_skeleton_workbook}"
SRC_URI += "git://csibmc@csibmc.visualstudio.com:22/G50/_git/skeleton;protocol=ssh;"

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/*"

# RDEPEND on pflash if the openpower-pflash machine feature is set.
PACKAGECONFIG ??= "${@bb.utils.contains('MACHINE_FEATURES', 'openpower-pflash', 'openpower-pflash', '', d)}"
PACKAGECONFIG[openpower-pflash] = ",,,pflash"

SRCREV = "d5ce56095e8ab69a4fbe3f3590cd2d9c96de6f31"

S = "${WORKDIR}"

# needed to invoke setuptools
export STAGING_INCDIR
export STAGING_LIBDIR
export BUILD_SYS
export HOST_SYS
export PYTHON_SITEPACKAGES_DIR

do_compile() {
        oe_runmake -C git PYTHON=${PYTHON}
}

do_install() {
        oe_runmake -C git install \
                PYTHON=${PYTHON} \
                DESTDIR=${D} \
                PREFIX=/usr
}