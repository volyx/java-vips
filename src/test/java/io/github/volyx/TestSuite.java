package io.github.volyx;

import org.junit.Assert;
import org.junit.Test;
import vips.VipsLibrary;

public class TestSuite {
	@Test
	public void test() {
		final String version = VipsLibrary.INSTANCE.vips_version_string();
		Assert.assertNotNull(version);
		System.out.println("Version: " + version);
	}
}
