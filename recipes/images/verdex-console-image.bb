# console image for gumstix verdex(pro)

inherit image

DEPENDS = "task-base-gumstix"

IMAGE_EXTRA_INSTALL ?= ""

AUDIO_INSTALL = " \
  alsa-utils \
  alsa-utils-aplay \
  alsa-utils-amixer \
  angstrom-zeroconf-audio \
 "

BASE_INSTALL = " \
  task-base-gumstix-extended \
  libpam \
 "

FIRMWARE_INSTALL = " \
#  linux-firmware \
  libertas-cf-firmware \
  rt73-firmware \
  zd1211-firmware \
 "

TOOLS_INSTALL = " \
  bash \
  bzip2 \
  ckermit \
  devmem2 \
  dhcp-client \
  dosfstools \
  fbgrab \
  fbset \
  fbset-modes \
  i2c-tools \
  ksymoops \
  mkfs-jffs2 \
  mtd-utils \
  nano \
  ntp ntpdate \
  openssh-misc \
  openssh-scp \
  openssh-ssh \
  procps \
  socat \
  strace \
  sudo \
  task-proper-tools \
#  u-boot-tools-env \
 "

IMAGE_INSTALL += " \
  ${BASE_INSTALL} \
  ${AUDIO_INSTALL} \
  ${FIRMWARE_INSTALL} \
  ${IMAGE_EXTRA_INSTALL} \
  ${TOOLS_INSTALL} \
 "

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

#ROOTFS_POSTPROCESS_COMMAND += '${@base_conditional("DISTRO_TYPE", "release", "zap_root_password; ", "",d)}'


