package io.github.volyx;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import vips.VipsForeignLoadClass;
import vips.VipsImage;
import vips.VipsLibrary;

public class Main {

    private static final VipsLibrary.gboolean GBOOLEAN_1 = new VipsLibrary.gboolean() {
        public VipsForeignLoadClass.gboolean_callback apply(Pointer VipsOperationBuildFn) {
            return new VipsForeignLoadClass.gboolean_callback() {
                public int apply(Pointer filename) {
                    return 1;
                }
            };
        }
    };
    public static final VipsLibrary INSTANCE = VipsLibrary.INSTANCE;

    public static void main(String[] args) {
        Native.setProtected(true);
        System.out.println(INSTANCE.vips_version_string());

//        final Vips vips = new Vips();

        final int initialize = INSTANCE.vips_init("img");

//        final int initialize = library.vips_initialize();

        if (initialize != 0 ) {
            INSTANCE.vips_shutdown();
            throw new RuntimeException();
        }

        // Disable libvips cache. Since processing pipeline is fine tuned, we won't get much profit from it.
        // Enabled cache can cause SIGSEGV on Musl-based systems like Alpine.
        INSTANCE.vips_cache_set_max_mem(new NativeSize(0));
        INSTANCE.vips_cache_set_max(0);

        VipsLibrary.INSTANCE.vips_leak_set(GBOOLEAN_1);
        VipsLibrary.INSTANCE.vips_cache_set_trace(GBOOLEAN_1);

        new Main().vipsLoadImage(new byte[] {0}, 1, 1);




    }

    public VipsImage vipsLoadImage(byte[] data, int imgtype, int shrink) {
        VipsImage.ByReference[] vipsImage = new VipsImage.ByReference[] {new VipsImage.ByReference()};

        // allocate sufficient native memory to hold the java array
        Memory ptr = new Memory(data.length);


// Copy the java array's contents to the native memory
        ptr.write(0, data, 0, data.length);

        if (0 != INSTANCE.vips_jpegload_buffer(ptr, new NativeSize(ptr.size()), vipsImage, "access", VipsLibrary.VipsAccess.VIPS_ACCESS_SEQUENTIAL, "shrink", 1, null)) {
            throw new RuntimeException("vips error");
        }
        return vipsImage[0];
    }
//    vips_jpegload_buffer(buf, len, out, "access", VIPS_ACCESS_SEQUENTIAL, "shrink", shrink, NULL);
}
