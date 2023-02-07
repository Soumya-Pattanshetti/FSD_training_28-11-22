import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DealerDashboardComponent } from './Component/dealer-dashboard/dealer-dashboard.component';
import { AdminDashboardComponent } from './Component/admin-dashboard/admin-dashboard.component';
import { LoginComponent } from './login/login.component';
import { AllOrdersComponent } from './Component/all-orders/all-orders.component';

@NgModule({
  declarations: [
    AppComponent,
    DealerDashboardComponent,
    AdminDashboardComponent,
    LoginComponent,
    AllOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
