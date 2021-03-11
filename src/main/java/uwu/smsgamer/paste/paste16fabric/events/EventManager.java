package uwu.smsgamer.paste.paste16fabric.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Logger;

public class EventManager {
    private static final Logger logger = Logger.getLogger("PasteEventManager");
    private static final HashMap<Class<? extends Event>, Set<ObjMethod>> listeners = new HashMap<>();

    public static void registerListener(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            for (Annotation declaredAnnotation : method.getDeclaredAnnotations()) {
                if (declaredAnnotation.annotationType().equals(PasteListener.class)) {
                    registerListener(method, obj);
                    break;
                }
            }
        }
    }

    public static void registerListener(Method method, Object obj) {
        if (method.getParameterCount() == 1) {
            Class<?> type = method.getParameterTypes()[0];
            if (Event.class.isAssignableFrom(type)) {
                Class<? extends Event> eClass = (Class<? extends Event>) type;
                method.setAccessible(true);
                listeners.computeIfAbsent(eClass, x -> new HashSet<>());
                listeners.get(eClass).add(new ObjMethod(obj, method));
            } else {
                logger.warning(method.getName() + "'s parameter is not of type Event! Actual type: " + type);
            }
        } else {
            logger.warning(method.getName() + " has more than one parameter count! Actual count: " + method.getParameterCount());
        }
    }

    public static void call(Event event) {
        Set<ObjMethod> objMethods = listeners.get(event.getClass());
        if (objMethods != null) for (ObjMethod m : objMethods) m.invoke(event);
    }

    private static class ObjMethod {
        public final Object obj;
        public final Method method;

        public ObjMethod(Object obj, Method method) {
            this.obj = obj;
            this.method = method;
        }

        public Object invoke(Object... params) {
            try {
                return method.invoke(obj, params);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
