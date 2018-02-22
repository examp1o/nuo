import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CrowdfundingWalletMySuffixComponent } from './crowdfunding-wallet-my-suffix.component';
import { CrowdfundingWalletMySuffixDetailComponent } from './crowdfunding-wallet-my-suffix-detail.component';
import { CrowdfundingWalletMySuffixPopupComponent } from './crowdfunding-wallet-my-suffix-dialog.component';
import { CrowdfundingWalletMySuffixDeletePopupComponent } from './crowdfunding-wallet-my-suffix-delete-dialog.component';

export const crowdfundingWalletRoute: Routes = [
    {
        path: 'crowdfunding-wallet-my-suffix',
        component: CrowdfundingWalletMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.crowdfundingWallet.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'crowdfunding-wallet-my-suffix/:id',
        component: CrowdfundingWalletMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.crowdfundingWallet.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const crowdfundingWalletPopupRoute: Routes = [
    {
        path: 'crowdfunding-wallet-my-suffix-new',
        component: CrowdfundingWalletMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.crowdfundingWallet.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'crowdfunding-wallet-my-suffix/:id/edit',
        component: CrowdfundingWalletMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.crowdfundingWallet.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'crowdfunding-wallet-my-suffix/:id/delete',
        component: CrowdfundingWalletMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.crowdfundingWallet.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
