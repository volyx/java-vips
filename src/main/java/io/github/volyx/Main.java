package io.github.volyx;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import org.bridj.Pointer;
import vips.VipsLibrary;

public class Main {

    public static void main(String[] args) {

        final VipsLibrary library = VipsLibrary.INSTANCE;
        System.out.println(library.vips_version_string());

    }
}
