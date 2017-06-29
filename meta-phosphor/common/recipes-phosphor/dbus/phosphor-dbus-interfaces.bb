SUMMARY = "Phosphor DBus Interfaces"
DESCRIPTION = "Generated bindings, using sdbus++, for the phosphor YAML"
PR = "r1"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
inherit obmc-phosphor-license
inherit pythonnative
inherit phosphor-dbus-yaml

DEPENDS += "autoconf-archive-native"
DEPENDS += "sdbus++-native"

SRC_URI += "git://github.com/openbmc/phosphor-dbus-interfaces"
SRCREV = "97d995d8c93a8e1c1ab959ae259f95d3651806b7"

DEPENDS_remove_class-native = "sdbus++-native"
DEPENDS_remove_class-nativesdk = "sdbus++-native"

PACKAGECONFIG ??= "libphosphor_dbus"
PACKAGECONFIG[libphosphor_dbus] = " \
        --enable-libphosphor_dbus, \
        --disable-libphosphor_dbus, \
        systemd sdbusplus, \
        libsystemd sdbusplus \
        "

PACKAGECONFIG_remove_class-native = "libphosphor_dbus"
PACKAGECONFIG_remove_class-nativesdk = "libphosphor_dbus"

BBCLASSEXTEND += "native nativesdk"