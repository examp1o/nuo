import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CrowdfundingWalletMySuffix } from './crowdfunding-wallet-my-suffix.model';
import { CrowdfundingWalletMySuffixPopupService } from './crowdfunding-wallet-my-suffix-popup.service';
import { CrowdfundingWalletMySuffixService } from './crowdfunding-wallet-my-suffix.service';
import { ProjectMySuffix, ProjectMySuffixService } from '../project-my-suffix';

@Component({
    selector: 'jhi-crowdfunding-wallet-my-suffix-dialog',
    templateUrl: './crowdfunding-wallet-my-suffix-dialog.component.html'
})
export class CrowdfundingWalletMySuffixDialogComponent implements OnInit {

    crowdfundingWallet: CrowdfundingWalletMySuffix;
    isSaving: boolean;

    projects: ProjectMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private crowdfundingWalletService: CrowdfundingWalletMySuffixService,
        private projectService: ProjectMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.projectService
            .query({filter: 'crowdfundingwallet-is-null'})
            .subscribe((res: HttpResponse<ProjectMySuffix[]>) => {
                if (!this.crowdfundingWallet.projectId) {
                    this.projects = res.body;
                } else {
                    this.projectService
                        .find(this.crowdfundingWallet.projectId)
                        .subscribe((subRes: HttpResponse<ProjectMySuffix>) => {
                            this.projects = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.crowdfundingWallet.id !== undefined) {
            this.subscribeToSaveResponse(
                this.crowdfundingWalletService.update(this.crowdfundingWallet));
        } else {
            this.subscribeToSaveResponse(
                this.crowdfundingWalletService.create(this.crowdfundingWallet));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CrowdfundingWalletMySuffix>>) {
        result.subscribe((res: HttpResponse<CrowdfundingWalletMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CrowdfundingWalletMySuffix) {
        this.eventManager.broadcast({ name: 'crowdfundingWalletListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProjectById(index: number, item: ProjectMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-crowdfunding-wallet-my-suffix-popup',
    template: ''
})
export class CrowdfundingWalletMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private crowdfundingWalletPopupService: CrowdfundingWalletMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.crowdfundingWalletPopupService
                    .open(CrowdfundingWalletMySuffixDialogComponent as Component, params['id']);
            } else {
                this.crowdfundingWalletPopupService
                    .open(CrowdfundingWalletMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
