import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectMySuffix } from './project-my-suffix.model';
import { ProjectMySuffixService } from './project-my-suffix.service';

@Component({
    selector: 'jhi-project-my-suffix-detail',
    templateUrl: './project-my-suffix-detail.component.html'
})
export class ProjectMySuffixDetailComponent implements OnInit, OnDestroy {

    project: ProjectMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private projectService: ProjectMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProjects();
    }

    load(id) {
        this.projectService.find(id)
            .subscribe((projectResponse: HttpResponse<ProjectMySuffix>) => {
                this.project = projectResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProjects() {
        this.eventSubscriber = this.eventManager.subscribe(
            'projectListModification',
            (response) => this.load(this.project.id)
        );
    }
}
