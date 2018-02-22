import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ProjectMySuffixComponent } from './project-my-suffix.component';
import { ProjectMySuffixDetailComponent } from './project-my-suffix-detail.component';
import { ProjectMySuffixPopupComponent } from './project-my-suffix-dialog.component';
import { ProjectMySuffixDeletePopupComponent } from './project-my-suffix-delete-dialog.component';

@Injectable()
export class ProjectMySuffixResolvePagingParams implements Resolve<any> {

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

export const projectRoute: Routes = [
    {
        path: 'project-my-suffix',
        component: ProjectMySuffixComponent,
        resolve: {
            'pagingParams': ProjectMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.project.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'project-my-suffix/:id',
        component: ProjectMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.project.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const projectPopupRoute: Routes = [
    {
        path: 'project-my-suffix-new',
        component: ProjectMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.project.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'project-my-suffix/:id/edit',
        component: ProjectMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.project.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'project-my-suffix/:id/delete',
        component: ProjectMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.project.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
