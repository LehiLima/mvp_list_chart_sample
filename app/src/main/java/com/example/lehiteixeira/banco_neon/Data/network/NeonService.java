/*
 * Copyright (c) Joaquim Ley 2016. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.lehiteixeira.banco_neon.Data.network;


import com.example.lehiteixeira.banco_neon.Data.Model.Transfer;

import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NeonService {

    /**
     * Retrieve token
     */
    @GET("GenerateToken")
    Call<String> getGenareteToken(@Query("nome") String name,
                                  @Query("email") String email);
    /**
     * Send money
     */
    @POST("SendMoney")
    Call<Boolean> postSendMoney(@Body HashMap<String, Object> body);
    /**
     * Retrieve list of sended money
     */
    @GET("GetTransfers")
    Call<List<Transfer>> getTranfers(@Query("token") String token);

}


