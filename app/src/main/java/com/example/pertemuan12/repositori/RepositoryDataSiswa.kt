
package com.example.pertemuan12.repositori

import com.example.pertemuan12.apiservice.ServiceApiSiswa
import com.example.pertemuan12.modeldata.DataSiswa
import retrofit2.Response

interface RepositoryDataSiswa {

    suspend fun getSiswa(): List<DataSiswa>

    suspend fun postSiswa(
        dataSiswa: DataSiswa
    ): Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoryDataSiswa {

    override suspend fun getSiswa(): List<DataSiswa> =
        serviceApiSiswa.getSiswa()

    override suspend fun postSiswa(
        dataSiswa: DataSiswa
    ): Response<Void> =
        serviceApiSiswa.postSiswa(dataSiswa)
}
