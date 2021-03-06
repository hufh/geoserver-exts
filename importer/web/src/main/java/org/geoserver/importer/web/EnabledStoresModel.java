package org.geoserver.importer.web;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.geoserver.catalog.StoreInfo;
import org.geoserver.web.data.store.StoresModel;

public class EnabledStoresModel extends LoadableDetachableModel {

    IModel<List<StoreInfo>> model;

    public EnabledStoresModel(IModel wsModel) {
        model = new StoresModel(wsModel);
    }

    @Override
    protected Object load() {
        List<StoreInfo> stores = model.getObject();
        for (Iterator<StoreInfo> it = stores.iterator(); it.hasNext();) {
            if (!it.next().isEnabled()) {
                it.remove();
            }
        }
        return stores;
    }

    @Override
    public void detach() {
        super.detach();
        if (model != null) {
            model.detach();
        }
    }
    
}
