import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectMySuffix } from './project-my-suffix.model';
import { ProjectMySuffixPopupService } from './project-my-suffix-popup.service';
import { ProjectMySuffixService } from './project-my-suffix.service';

@Component({
    selector: 'jhi-project-my-suffix-delete-dialog',
    templateUrl: './project-my-suffix-delete-dialog.component.html'
})
export class ProjectMySuffixDeleteDialogComponent {

    project: ProjectMySuffix;

    constructor(
        private projectService: ProjectMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.projectService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'projectListModification',
                content: 'Deleted an project'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-project-my-suffix-delete-popup',
    template: ''
})
export class ProjectMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private projectPopupService: ProjectMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.projectPopupService
                .open(ProjectMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
