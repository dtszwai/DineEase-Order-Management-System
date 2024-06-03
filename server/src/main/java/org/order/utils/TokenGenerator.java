package org.order.utils;

import java.util.Random;

/**
 * This class is used to generate a random token string. The token string is composed of uppercase
 * and lowercase letters and numbers. The length of the token string is defined by the TOKEN_LENGTH
 * constant.
 */
public class TokenGenerator {
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  private static final int TOKEN_LENGTH = 6;

  private static final Random RANDOM = new Random();

  /**
   * Generates a random token string. The token string is composed of uppercase and lowercase
   * letters and numbers. The length of the token string is defined by the TOKEN_LENGTH constant.
   *
   * @return the generated token string.
   */
  public static String generateToken() {
    StringBuilder token = new StringBuilder(TOKEN_LENGTH);
    for (int i = 0; i < TOKEN_LENGTH; i++) {
      int index = RANDOM.nextInt(CHARACTERS.length());
      token.append(CHARACTERS.charAt(index));
    }
    return token.toString();
  }
}