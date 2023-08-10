set -o errexit
set -o errtrace
set -o pipefail
[[ "$TRACE" ]] && set -x

main () {
  local OS;
  local ARCH;
  local OUT;
  OS="$(uname -s)"
  ARCH="$(uname -m)"
  OUT="out"


  echo "$OS" "$ARCH" "$*"

  processing-java --sketch="$PWD/NO_DANCING" --output="$PWD/$OUT" --force --variant='macos-aarch64' --export
  cp -r "$HOME/Documents/Processing/libraries/Dmx4Artists" "$OUT/NO_DANCING.app/Contents/Java"

}

main "$@"

