require u-boot.inc

PR = "r0"

S = "${WORKDIR}/u-boot-${PV}"

SRC_URI = "ftp://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2"

SRC_URI_append_gumstix-verdex = " \
	file://common/add-gumstix-board-to-build.patch;patch=1 \
	file://common/autoscript.patch;patch=1 \
	file://common/compact-flash.patch;patch=1 \
	file://common/crc-warning-not-so-scary.patch;patch=1 \
	file://common/do-not-build-examples.patch;patch=1 \
	file://common/ethernet-fixups.patch;patch=1 \
	file://common/fat-bss-reduction.patch;patch=1 \
	file://common/flash-protect-fixup.patch;patch=1 \
	file://common/fw_printenv.patch;patch=1 \
	file://common/gcc-40-fixups.patch;patch=1 \
	file://common/gumstix-otp-serial-programmer.patch;patch=1 \
	file://common/install.patch;patch=1 \
	file://common/jerase-cmd.patch;patch=1 \
	file://common/jffs2-new-nodetypes.patch;patch=1 \
	file://common/kernel-at-top.patch;patch=1 \
	file://common/loadb-safe.patch;patch=1 \
	file://common/move-examples-address.patch;patch=1 \
	file://common/osx.patch;patch=1 \
	file://common/pxa-regs-fixup.patch;patch=1 \
	file://common/pxa-sd-driver.patch;patch=1 \
	file://common/pxa-serial-highspeed.patch;patch=1 \
	file://common/smc91x-multi.patch;patch=1 \
	file://verdex/base.patch;patch=1 \
	file://verdex/smc911x-driver.patch;patch=1 \
	file://verdex/use-smc91C111-for-now.patch;patch=1 \
	file://common/env-Makefile.patch;patch=1 \
	file://common/tools-Makefile.patch;patch=1 \
	"

# just set the variable manually
GUMSTIX_400MHZ="y"

def gumstix_mhz(d):
	import bb
        m = bb.data.getVar('GUMSTIX_400MHZ', d, 1)
	if 'y' == m:
		return '400'
	else:
		return '200'

EXTRA_OEMAKE_gumstix-verdex = "CROSS_COMPILE=${TARGET_PREFIX} GUMSTIX_400MHZ=${GUMSTIX_400MHZ}"
TARGET_LDFLAGS = ""

UBOOT_IMAGE_gumstix-verdex = "u-boot-${MACHINE}-${@gumstix_mhz(d)}Mhz-${PV}-${PR}.bin"

inherit base

do_compile () {
	# specify gumstix_config directly rather than dealing with
	# machine type 'gumstix-verdex directly
	#oe_runmake ${UBOOT_MACHINE}
	oe_runmake gumstix_config
	oe_runmake all
}
