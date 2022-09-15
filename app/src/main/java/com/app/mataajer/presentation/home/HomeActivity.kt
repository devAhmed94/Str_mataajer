package com.app.mataajer.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult.RESULT_IN_APP_UPDATE_FAILED
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.app.mataajer.R
import com.app.mataajer.core.BaseActivity
import com.app.mataajer.data.application.REQUEST_FLEXIBLE_UPDATE
import com.app.mataajer.data.db.DBHelperImpl
import com.app.mataajer.data.db.database.MyDatabase
import com.app.mataajer.data.repo.Repository
import com.app.mataajer.databinding.ActivityHomeBinding

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
class HomeActivity : BaseActivity<ActivityHomeBinding>(), InstallStateUpdatedListener {

    private val dbHelper by lazy { DBHelperImpl(MyDatabase.DatabaseBuilder.getInstance(this)) }
    private val factory by lazy { MainViewModelFactory(Repository(dbHelper)) }
    val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkUpdate()
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) notifyUser()
        }
    }

    override fun onDestroy() {
        appUpdateManager.unregisterListener(this)
        super.onDestroy()
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_FLEXIBLE_UPDATE -> when (resultCode) {
                RESULT_OK -> {
                    // User has accepted the request for update.
                }
                RESULT_CANCELED -> {
                    // User has canceled the update or denied for the an update.
                }
                RESULT_IN_APP_UPDATE_FAILED -> {
                    // Some error has occurred during the update.
                    // It may be some error from the user side or some other error.
                }
            }
        }
    }

    override fun onStateUpdate(installState: InstallState) {
        if (installState.installStatus() == InstallStatus.DOWNLOADED) notifyUser()
    }

    private fun checkUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                it.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    it,
                    AppUpdateType.FLEXIBLE, this, REQUEST_FLEXIBLE_UPDATE
                )
            }
        }
    }

    private fun notifyUser() {
        Snackbar
            .make(
                binding.rlContainer,
                R.string.base_restart_msg,
                BaseTransientBottomBar.LENGTH_INDEFINITE
            )
            .setAction(R.string.base_restart) {
                appUpdateManager.completeUpdate()
                appUpdateManager.unregisterListener(this)
            }.show()
    }
}