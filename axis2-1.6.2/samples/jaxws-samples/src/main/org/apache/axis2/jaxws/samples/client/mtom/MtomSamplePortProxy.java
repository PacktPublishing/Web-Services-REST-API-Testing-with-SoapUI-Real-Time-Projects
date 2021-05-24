/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axis2.jaxws.samples.client.mtom;

import org.apache.axis2.jaxws.samples.mtom.ImageDepot;
import org.apache.axis2.jaxws.samples.mtom.MtomSample;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.net.URL;

public class MtomSamplePortProxy {

    protected Descriptor _descriptor;

    public class Descriptor {
        private MtomSampleService _service = null;
        private MtomSample _proxy = null;
        private Dispatch<Source> _dispatch = null;

        public Descriptor() {
            _service = new MtomSampleService();
            initCommon();
        }

        public Descriptor(URL wsdlLocation) {
            _service = new MtomSampleService(wsdlLocation, new QName("http://org/apache/axis2/jaxws/samples/mtom/", "MtomSampleService"));
            initCommon();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new MtomSampleService(wsdlLocation, serviceName);
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getMtomSamplePort();
        }

        public MtomSample getProxy() {
            return _proxy;
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null) {
                QName portQName = new QName("http://org/apache/axis2/jaxws/samples/mtom/", "MtomSamplePort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }
    }

    public MtomSamplePortProxy() {
        _descriptor = new Descriptor();
    }

    public MtomSamplePortProxy(URL wsdlLocation) {
        _descriptor = new Descriptor(wsdlLocation);
    }

    public MtomSamplePortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public ImageDepot sendImage(ImageDepot input) {
        return _getDescriptor().getProxy().sendImage(input);
    }

}