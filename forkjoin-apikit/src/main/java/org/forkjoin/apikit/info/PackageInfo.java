package org.forkjoin.apikit.info;

import com.google.common.base.Supplier;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class PackageInfo<T extends ModuleInfo> {
    private Multimap<String, T> multimap = Multimaps.newListMultimap(
            new ConcurrentSkipListMap<String, Collection<T>>(), new Supplier<List<T>>() {
                @Override
                public List<T> get() {
                    return new CopyOnWriteArrayList<>();
                }
            }
    );

    public void add(String pack, T t) {
        multimap.put(pack, t);
    }

    public Collection<T> getValues() {
        return multimap.values();
    }

    public Map<String, Collection<T>> getAll() {
        return multimap.asMap();
    }

    public Collection<Map.Entry<String, T>> getEntries() {
        return multimap.entries();
    }

    public T getByType(TypeInfo typeInfo) {
        String packageName = typeInfo.getPackageName();
        if(packageName != null){
            Collection<T> ts = multimap.get(packageName);
            Optional<T> any = ts.stream().filter(r -> r.getName().equals(typeInfo.getName())).findAny();
            return any.orElseGet(() -> null);
        }else{
            return null;
        }
    }
}
