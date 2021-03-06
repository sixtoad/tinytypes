package tech.anima.tinytypes.jersey;

import java.lang.reflect.Field;
import java.util.Set;
import javax.ws.rs.ext.RuntimeDelegate;
import org.glassfish.jersey.internal.AbstractRuntimeDelegate;
import org.glassfish.jersey.server.internal.RuntimeDelegateImpl;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

/**
 * Class to register TinyTypes support on Jersey response headers.
 */
public class JerseyResponseSupport {

    /**
     * Registers Jersey HeaderDelegateProviders for the specified TinyTypes.
     *
     * @param head a TinyType
     * @param tail other TinyTypes
     * @throws IllegalArgumentException when a non-TinyType is given
     */
    public static void registerTinyTypes(Class<?> head, Class<?>... tail) {
        final Set<HeaderDelegateProvider> systemRegisteredHeaderProviders = stealAcquireRefToHeaderDelegateProviders();
        register(head, systemRegisteredHeaderProviders);
        for (Class<?> tt : tail) {
            register(tt, systemRegisteredHeaderProviders);
        }
    }

    private static void register(Class<?> candidate, final Set<HeaderDelegateProvider> systemRegisteredHeaderProviders) {
        systemRegisteredHeaderProviders.add(new TinyTypesHeaderDelegateProvider(candidate));
    }

    private static Set<HeaderDelegateProvider> stealAcquireRefToHeaderDelegateProviders() {
        try {
            final RuntimeDelegate instance = RuntimeDelegate.getInstance();
            final RuntimeDelegateImpl s = (RuntimeDelegateImpl) instance;
            final Field declaredField;
            declaredField = AbstractRuntimeDelegate.class.getDeclaredField("hps");
            declaredField.setAccessible(true);
            final Set<HeaderDelegateProvider> get = (Set<HeaderDelegateProvider>) declaredField.get(s);
            return get;
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

}
