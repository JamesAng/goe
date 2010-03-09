DESCRIPTION = "Firmware and Install for Marvell libertas CF 88W8385"
LICENSE = "unknown"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "GPL"
RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"

PR = "r1"

# Initialize the wifi device at S38 before networking script S40 is run 
INITSCRIPT_NAME = "cf8385"
INITSCRIPT_PARAMS = "start 38 S . stop 38 S 0 6 1 ."

SRC_URI = "file://libertas_cs.fw \
           file://libertas_cs_helper.fw \
	   file://cf8385.init"

S = "${WORKDIR}"

inherit autotools update-rc.d

do_install() {
	install -d ${D}/${base_libdir}/firmware
	install -m 0644 ${S}/libertas_cs.fw ${D}/${base_libdir}/firmware/
	install -m 0644 ${S}/libertas_cs_helper.fw ${D}/${base_libdir}/firmware/
	install	-d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/cf8385.init ${D}${sysconfdir}/init.d/cf8385
}

FILES_${PN} = "${base_libdir}/firmware/"
FILES_${PN} += "${base_libdir}/modules/"
FILES_${PN} += "${sysconfdir}/"

PACKAGE_ARCH = "all"

