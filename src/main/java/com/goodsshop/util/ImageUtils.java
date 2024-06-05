package com.goodsshop.util;

import java.util.Base64;

public class ImageUtils {
	public static String encodeImage(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
