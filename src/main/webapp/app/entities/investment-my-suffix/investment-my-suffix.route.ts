import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { InvestmentMySuffixComponent } from './investment-my-suffix.component';
import { InvestmentMySuffixDetailComponent } from './investment-my-suffix-detail.component';
import { InvestmentMySuffixPopupComponent } from './investment-my-suffix-dialog.component';
import { InvestmentMySuffixDeletePopupComponent } from './investment-my-suffix-delete-dialog.component';

@Injectable()
export class InvestmentMySuffixResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const investmentRoute: Routes = [
    {
        path: 'investment-my-suffix',
        component: InvestmentMySuffixComponent,
        resolve: {
            'pagingParams': InvestmentMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.investment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'investment-my-suffix/:id',
        component: InvestmentMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.investment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const investmentPopupRoute: Routes = [
    {
        path: 'investment-my-suffix-new',
        component: InvestmentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.investment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'investment-my-suffix/:id/edit',
        component: InvestmentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.investment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'investment-my-suffix/:id/delete',
        component: InvestmentMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.investment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
