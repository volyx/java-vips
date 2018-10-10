package io.github.volyx;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import org.bridj.Pointer;
import vips.VipsLibrary;

public class Main {

    public static void main(String[] args) {

        final VipsLibrary library = VipsLibrary.INSTANCE;
        System.out.println(library.vips_version_string());

//        final Vips vips = new Vips();

        final int initialize = library.vips_init("img");

//        final int initialize = library.vips_initialize();

        if (initialize != 0 ) {
            library.vips_shutdown();
            throw new RuntimeException();
        }

        // Disable libvips cache. Since processing pipeline is fine tuned, we won't get much profit from it.
        // Enabled cache can cause SIGSEGV on Musl-based systems like Alpine.
        library.vips_cache_set_max_mem(new NativeSize(0));
        library.vips_cache_set_max(0);
    }
}
