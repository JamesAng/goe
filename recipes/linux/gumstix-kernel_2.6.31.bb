require linux.inc

S = "${WORKDIR}/linux-${PV}"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}.tar.bz2 \
	file://defconfig \
	"

SRC_URI_append = " \
	file://no-empty-flash-warnings.patch;patch=1 \
	file://fix-install.patch;patch=1 \
	file://gumstix-2.6.30.patch;patch=1 \
	file://tsc2003-config.patch;patch=1 \
	file://header.patch;patch=1 \
	file://compact-flash.patch;patch=1 \
	file://flash.patch;patch=1 \
	file://proc-gpio.patch;patch=1 \
	file://modular-init-bluetooth.patch;patch=1 \
	file://quiet-single-block-retry-warning.patch;patch=1 \
	file://smsc911x.patch;patch=1 \
	file://i2c-include.patch;patch=1 \
	"


do_configure_prepend() {
       # turn off frame buffer support in kernel if lcd MACHINE_FEATURES not defined    
       ${@base_contains('MACHINE_FEATURES', 'lcd','','sed -i "s/CONFIG_FB=m/# CONFIG_FB is not set/" ${WORKDIR}/defconfig',d)}
       ${@base_contains('MACHINE_FEATURES', 'lcd','','sed -i "s/CONFIG_FB_PXA=m/# CONFIG_FB_PXA is not set/" ${WORKDIR}/defconfig',d)}
       ${@base_contains('MACHINE_FEATURES', 'lcd','','sed -i "s/CONFIG_FRAMEBUFFER_CONSOLE=m/# CONFIG_FRAMEBUFFER_CONSOLE is not set/" ${WORKDIR}/defconfig',d)}
       ${@base_contains('MACHINE_FEATURES', 'lcd','','sed -i "s/CONFIG_LOGO=y/# CONFIG_LOGO is not set/" ${WORKDIR}/defconfig',d)}

       # if mmcroot MACHINE_FEATURES requested disable jffs2 and enable mmc and ext2 support in kernel
       ${@base_contains('MACHINE_FEATURES', 'mmcroot','sed -i "s/CONFIG_JFFS2_FS=y/CONFIG_JFFS2_FS=m/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'mmcroot','sed -i "s/CONFIG_EXT2_FS=m/CONFIG_EXT2_FS=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'mmcroot','sed -i "s/CONFIG_MMC=m/CONFIG_MMC=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'mmcroot','sed -i "s/CONFIG_MMC_PXA=m/CONFIG_MMC_PXA=y/" ${WORKDIR}/defconfig','',d)} 
       ${@base_contains('MACHINE_FEATURES', 'mmcroot','sed -i "s/CONFIG_MMC_BLOCK=m/CONFIG_MMC_BLOCK=y/" ${WORKDIR}/defconfig','',d)} 

       # if cfroot MACHINE_FEATURES requested disable jffs2 and enable pcmcia and ext2 support in kernel
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_JFFS2_FS=y/CONFIG_JFFS2_FS=m/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_EXT2_FS=m/CONFIG_EXT2_FS=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_PCCARD=m/CONFIG_PCCARD=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_PCMCIA=m/CONFIG_PCMCIA=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_PCMCIA_PXA2XX=m/CONFIG_PCMCIA_PXA2XX=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_IDE=m/CONFIG_IDE=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_BLK_DEV_IDE=m/CONFIG_BLK_DEV_IDE=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_BLK_DEV_IDEDISK=m/CONFIG_BLK_DEV_IDEDISK=y/" ${WORKDIR}/defconfig','',d)}
       ${@base_contains('MACHINE_FEATURES', 'cfroot','sed -i "s/CONFIG_BLK_DEV_IDECS=m/CONFIG_BLK_DEV_IDECS=y/" ${WORKDIR}/defconfig','',d)}
}

