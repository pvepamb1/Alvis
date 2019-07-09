mkdir build/gpg

echo $KEY_WRAP > build/gpg/keywrap
echo $KEY_PRIV > build/gpg/keypriv

DIR="$(pwd)"

cat gradle/gpg/myencryptedkey.asc.aes256 | \
  gpg -d --no-tty --no-use-agent --passphrase-file=build/gpg/keywrap \
  > build/gpg/mykey.asc

gpg --homedir=build/gpg --no-tty --no-use-agent \
  --import build/gpg/mykey.asc

cd build/debian/$1

debuild -kD3B3EA2E -S -p"gpg --no-tty \
--homedir=$DIR/build/gpg  --no-use-agent --passphrase-file=$DIR/build/gpg/keypriv"

dput -d --config ../../../gradle/script/dput.cf homeauto ../*.changes
