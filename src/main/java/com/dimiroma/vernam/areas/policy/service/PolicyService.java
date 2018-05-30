package com.dimiroma.vernam.areas.policy.service;

import java.io.InputStream;

public interface PolicyService {
    InputStream getPdfById(final Long id);
}
