# X11 demo palmtop GPE image for gumstix verdex(pro)
# Note: Image size is quite large so needs to be on SD card
require verdex-console-image.bb

SPLASH ?= "psplash"

IMAGE_INSTALL += " \
  angstrom-gpe-task-apps \
  angstrom-gpe-task-base \
  angstrom-gpe-task-game \
  angstrom-gpe-task-pim \
  angstrom-gpe-task-settings \
  angstrom-x11-base-depends \
  cellwriter \
  midori \
  mplayer \
  ${SPLASH} \
  xlsfonts \
  xmms \
  xrefresh \
 "

