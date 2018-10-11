[![Build Status](https://travis-ci.com/volyx/java-vips.svg?branch=master)](https://travis-ci.com/volyx/java-vips)

# Usage

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.volyx</groupId>
    <artifactId>java-vips</artifactId>
    <version>-SNAPSHOT</version>
</dependency>
```

# Installation

### From the source

Install [libvips](https://github.com/libvips/libvips).

```bash
# macOS
$ brew tap homebrew/science
$ brew install vips

# Ubuntu
# Ubuntu apt repository contains a pretty old version of libvips.
# It's recommended to use PPA with an up to date version.
$ sudo add-apt-repository ppa:dhor/myway
$ sudo apt-get install libvips-dev
```

**Note:** Most libvips packages come with WebP support. If you want libvips to support WebP on macOS, you need to install it this way:

```bash
$ brew tap homebrew/science
$ brew install vips --with-webp
```
