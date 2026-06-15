package com.triminds.tlp.security.tenant;

public final class MultiTenantContext {

    private static final ThreadLocal<String> CURRENT = new ThreadLocal<>();

    private MultiTenantContext() {
    }

    public static void setTenantId(String tenantId) {
        CURRENT.set(tenantId);
    }

    public static String getTenantId() {
        return CURRENT.get();
    }

    public static void clear() {
        CURRENT.remove();
    }
}
