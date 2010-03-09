DESCRIPTION = "Firmware and Install for Marvell libertas CF 88W8385"
LICENSE = "unknown"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "GPL"
RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"

SRCREV = "764d482304e3b13024184b05d907fe4c291faa07"

PV = "5.0.20"
PR = "r0"
PR_append = "+gitr${SRCREV}"

# Initialize the wifi device at S38 before networking script S40 is run 
INITSCRIPT_NAME = "cf8385"
INITSCRIPT_PARAMS = "start 38 S . stop 38 S 0 6 1 ."

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/dwmw2/linux-firmware.git;branch=master;protocol=git \
	   file://cf8385.init \
	   file://license.txt \
"

S = "${WORKDIR}/git"

inherit autotools update-rc.d

do_install() {
	install -d ${D}/${base_libdir}/firmware
	install -m 0644 ${S}/libertas/cf8385.bin ${D}/${base_libdir}/firmware/libertas_cs.fw
	install -m 0644 ${S}/libertas/cf8385_helper.bin ${D}/${base_libdir}/firmware/libertas_cs_helper.fw
	install -m 0444 ${WORKDIR}/license.txt ${D}/${base_libdir}/firmware/libertas_license.txt
	install	-d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/cf8385.init ${D}${sysconfdir}/init.d/cf8385
}

FILES_${PN} = "${base_libdir}/firmware/"
FILES_${PN} += "${base_libdir}/modules/"
FILES_${PN} += "${sysconfdir}/"

PACKAGE_ARCH = "all"

